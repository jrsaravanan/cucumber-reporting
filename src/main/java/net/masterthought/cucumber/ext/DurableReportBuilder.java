package net.masterthought.cucumber.ext;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.VelocityContextMap;
import net.masterthought.cucumber.charts.FlashChartBuilder;
import net.masterthought.cucumber.charts.JsChartUtil;
import net.masterthought.cucumber.ext.domain.entity.BuildEntity;
import net.masterthought.cucumber.ext.domain.entity.ElementEntity;
import net.masterthought.cucumber.ext.domain.entity.FeatureDetialsEntity;
import net.masterthought.cucumber.ext.domain.entity.FeatureEntity;
import net.masterthought.cucumber.ext.domain.entity.StepEntity;
import net.masterthought.cucumber.ext.domain.repository.BuildRepository;
import net.masterthought.cucumber.ext.domain.repository.GenericRepository;
import net.masterthought.cucumber.ext.domain.repository.GenericRepositoryImpl;
import net.masterthought.cucumber.json.Element;
import net.masterthought.cucumber.json.Feature;
import net.masterthought.cucumber.json.Step;
import net.masterthought.cucumber.util.Util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Saravanan Renganathan
 *
 */
public class DurableReportBuilder extends ReportBuilder {

	public static final Logger LOGGER = LoggerFactory.getLogger(DurableReportBuilder.class);
	private GenericRepository<FeatureEntity, Long> repository;
	private GenericRepository<FeatureDetialsEntity, Long> detailsRepository;
	private BuildRepository buildRepository;
	StaticReportInformation reportInformation = new StaticReportInformation();
	
	public DurableReportBuilder(List<String> jsonReports, File reportDirectory,
			String pluginUrlPath, String buildNumber, String buildProject,
			boolean skippedFails, boolean undefinedFails, boolean flashCharts,
			boolean runWithJenkins, boolean artifactsEnabled,
			String artifactConfig, boolean highCharts) throws Exception {
		
		super(jsonReports, reportDirectory, pluginUrlPath, buildNumber, buildProject,
				skippedFails, undefinedFails, flashCharts, runWithJenkins,
				artifactsEnabled, artifactConfig, highCharts);
		
		repository = new GenericRepositoryImpl<FeatureEntity, Long>() {
			
		};
		
		detailsRepository = new GenericRepositoryImpl<FeatureDetialsEntity, Long>() {
			
		};
		
		buildRepository = new BuildRepository() ;
	}
	
	public void generateDurableReport() throws Exception {
		
		persistReport();
		publishReportMetrics();
		repository.close();
		detailsRepository.close();
		buildRepository.close();
	}
	
	
	private void publishReportMetrics() throws Exception  {
		
		try {
            copyResource("themes", "blue.zip");
            if (flashCharts) {
                copyResource("charts", "flash_charts.zip");
            } else {
                copyResource("charts", "js.zip");
            }
            if (artifactsEnabled) {
                copyResource("charts", "codemirror.zip");
            }
            fetchFeatureDetails();
            generateFeatureMetrics();
		} catch (Exception exception) {
            if (!parsingError) {
                generateErrorPage(exception);
                System.out.println(exception);
                exception.printStackTrace();
            }
        }
	}
	
	
    private void fetchFeatureDetails() {
		List<FeatureDetialsEntity> list = detailsRepository.find(buildProject);
		
		for (FeatureDetialsEntity entity : list) {
			reportInformation.totalNumberOfFeatures++;
			reportInformation.totalNumberOfSteps += entity.getTotalSteps();
			reportInformation.totalNumberPassingSteps += entity.getStepPassed();
			reportInformation.totalNumberFailingSteps += entity.getStepFailed();
			reportInformation.totalNumberSkippedSteps += entity.getStepSkipped();
			reportInformation.totalNumberPendingSteps += entity.getStepPending();
			reportInformation.totalScenariosPassed += entity.getScenarioPassed();
			reportInformation.totalScenariosFailed  += entity.getScenarioFailed();
			reportInformation.totalNumberOfScenarios  += entity.getTotalScenario();
			reportInformation.getFeatures().add(entity);
		}
		
	}

	protected void generateFeatureMetrics() throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init(getProperties());
        Template featureOverview = ve.getTemplate("templates/featureMetrics.vm");
        VelocityContextMap contextMap = VelocityContextMap.of(new VelocityContext());
        contextMap.putAll(getGeneralParameters());
        contextMap.put("features", reportInformation.getFeatures());
        contextMap.put("total_features", reportInformation.getTotalNumberOfFeatures());
        contextMap.put("total_scenarios", reportInformation.getTotalNumberOfScenarios());
        contextMap.put("total_steps", reportInformation.getTotalNumberOfSteps());
        contextMap.put("total_passes", reportInformation.getTotalNumberPassingSteps());
        contextMap.put("total_fails", reportInformation.getTotalNumberFailingSteps());
        contextMap.put("total_skipped", reportInformation.getTotalNumberSkippedSteps());
        contextMap.put("total_pending", reportInformation.getTotalNumberPendingSteps());
        contextMap.put("scenarios_passed", reportInformation.getTotalScenariosPassed());
        contextMap.put("scenarios_failed", reportInformation.getTotalScenariosFailed());
        if (flashCharts) {
            contextMap.put("step_data", FlashChartBuilder.donutChart(reportInformation.getTotalNumberPassingSteps(), 
            		reportInformation.getTotalNumberFailingSteps(), reportInformation.getTotalNumberSkippedSteps(), reportInformation.getTotalNumberPendingSteps()));
            contextMap.put("scenario_data", FlashChartBuilder.pieChart(reportInformation.getTotalScenariosPassed(),
            		reportInformation.getTotalScenariosFailed()));
        } else {
            JsChartUtil pie = new JsChartUtil();
            List<String> stepColours = pie.orderStepsByValue(reportInformation.getTotalNumberPassingSteps(), reportInformation.getTotalNumberFailingSteps(), 
            		reportInformation.getTotalNumberSkippedSteps(), reportInformation.getTotalNumberPendingSteps());
            contextMap.put("step_data", stepColours);
            List<String> scenarioColours = pie.orderScenariosByValue(reportInformation.getTotalScenariosPassed(), reportInformation.getTotalScenariosFailed());
            contextMap.put("scenario_data", scenarioColours);
        }
        contextMap.put("time_stamp", ri.timeStamp());
        contextMap.put("total_duration", ri.getTotalDurationAsString());
        contextMap.put("flashCharts", flashCharts);
        contextMap.put("highCharts", highCharts);
        generateReport("feature-metrics.html", featureOverview, contextMap.getVelocityContext());
    }
    
	private void persistReport() {
		
		LOGGER.info("Json Reports {} ", jsonReports.toString());
		LOGGER.info("File Directory {} ", reportDirectory.toString());
		LOGGER.info("Build Number {} ", buildNumber);
		LOGGER.info("Build Project {} ", buildProject);
		
		saveJsonFiles();
		
		
		for (Feature feature : ri.getFeatures()) {
			
			feature.processSteps();
			
			FeatureEntity featureEntity = new FeatureEntity();
			FeatureDetialsEntity details  = new FeatureDetialsEntity();
			
			LOGGER.info("report name {} ", feature.getRawName());
			LOGGER.info("report number of scnerios {} ",feature.getNumberOfScenarios());
			
			featureEntity.setName(feature.getRawName());
			featureEntity.setDescription(feature.getRawDescription());
			featureEntity.setProjectName(buildProject);
			
			details.setName(feature.getRawName());
			details.setDescription(feature.getRawDescription());
			details.setProjectName(buildProject);
			details.setDuration(feature.getDurationOfSteps());
			details.setScenarioFailed(feature.getNumberOfScenariosFailed());
			details.setScenarioPassed(feature.getNumberOfScenariosPassed());
			details.setStatus(feature.getStatus().toString());
			details.setStepFailed(feature.getNumberOfFailures());
			details.setStepPassed(feature.getNumberOfPasses());
			details.setStepPending(feature.getNumberOfPending());
			details.setTotalSteps(feature.getNumberOfSteps());
			details.setTotalScenario(feature.getNumberOfScenarios());
			details.setStepUndefined(feature.getNumberOfUndefined());
			details.setStepMissing(feature.getNumberOfMissing());
			details.setStepSkipped(feature.getNumberOfSkipped());
			
			for (Element element : feature.getElements()) {
				LOGGER.info("element name {} ", element.getRawName());
				LOGGER.info("element number of step {} ",element.getSteps().size());
				ElementEntity elementEntity = new ElementEntity(element.getRawName(), null, featureEntity);
				featureEntity.getElements().add(elementEntity);

				for (Step step  : element.getSteps()) {
					StepEntity stepEntity  = new StepEntity(step.getRawName(), step.getOutput(),
							step.getStatus().toString(), null, step.getDuration(), elementEntity);
					elementEntity.getSteps().add(stepEntity);
				}
				 
			}
			
			detailsRepository.save(details);
			repository.save(featureEntity);
		}
	}

	private void saveJsonFiles() {
		
			for (String file : jsonReports) {
				
				try {
					String fileContent = Util.readFileAsString(file);
					BuildEntity buildEntity = new BuildEntity(buildProject, buildNumber, fileContent);
					buildRepository.save(buildEntity);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public void publishRecentReports() {
		
	}

}
