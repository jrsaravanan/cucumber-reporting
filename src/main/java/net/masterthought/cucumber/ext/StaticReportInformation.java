package net.masterthought.cucumber.ext;

import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.ext.domain.entity.FeatureDetialsEntity;


public class StaticReportInformation {

	
	private List<FeatureDetialsEntity> features;
	public int numberOfScenarios;
	public int numberOfSteps;
	public Long totalDuration = 0l;
	public int totalTagScenarios = 0;
	public int totalTagSteps = 0;
	public int totalTagPasses = 0;
	public int totalTagFails = 0;
	public int totalTagSkipped = 0;
	public int totalTagPending = 0;
	public long totalTagDuration = 0l;
	public int totalPassingTagScenarios = 0;
	public int totalFailingTagScenarios = 0;
	public int totalNumberOfFeatures = 0;
	public int totalNumberOfScenarios = 0;
	public int totalNumberOfSteps = 0 ;
	public int totalNumberPassingSteps = 0;
	public int totalNumberFailingSteps = 0;
	public int totalNumberSkippedSteps = 0;
	public int totalNumberPendingSteps = 0;
	public int totalScenariosPassed = 0;
	public int totalScenariosFailed = 0;
	public long duration = 0L;
	
	public int getNumberOfScenarios() {
		return numberOfScenarios;
	}
	public void setNumberOfScenarios(int numberOfScenarios) {
		this.numberOfScenarios = numberOfScenarios;
	}
	public int getNumberOfSteps() {
		return numberOfSteps;
	}
	public void setNumberOfSteps(int numberOfSteps) {
		this.numberOfSteps = numberOfSteps;
	}
	public Long getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}
	public int getTotalTagScenarios() {
		return totalTagScenarios;
	}
	public void setTotalTagScenarios(int totalTagScenarios) {
		this.totalTagScenarios = totalTagScenarios;
	}
	public int getTotalTagSteps() {
		return totalTagSteps;
	}
	public void setTotalTagSteps(int totalTagSteps) {
		this.totalTagSteps = totalTagSteps;
	}
	public int getTotalTagPasses() {
		return totalTagPasses;
	}
	public void setTotalTagPasses(int totalTagPasses) {
		this.totalTagPasses = totalTagPasses;
	}
	public int getTotalTagFails() {
		return totalTagFails;
	}
	public void setTotalTagFails(int totalTagFails) {
		this.totalTagFails = totalTagFails;
	}
	public int getTotalTagSkipped() {
		return totalTagSkipped;
	}
	public void setTotalTagSkipped(int totalTagSkipped) {
		this.totalTagSkipped = totalTagSkipped;
	}
	public int getTotalTagPending() {
		return totalTagPending;
	}
	public void setTotalTagPending(int totalTagPending) {
		this.totalTagPending = totalTagPending;
	}
	public long getTotalTagDuration() {
		return totalTagDuration;
	}
	public void setTotalTagDuration(long totalTagDuration) {
		this.totalTagDuration = totalTagDuration;
	}
	public int getTotalPassingTagScenarios() {
		return totalPassingTagScenarios;
	}
	public void setTotalPassingTagScenarios(int totalPassingTagScenarios) {
		this.totalPassingTagScenarios = totalPassingTagScenarios;
	}
	public int getTotalFailingTagScenarios() {
		return totalFailingTagScenarios;
	}
	public void setTotalFailingTagScenarios(int totalFailingTagScenarios) {
		this.totalFailingTagScenarios = totalFailingTagScenarios;
	}
	public int getTotalNumberOfFeatures() {
		return totalNumberOfFeatures;
	}
	public void setTotalNumberOfFeatures(int totalNumberOfFeatures) {
		this.totalNumberOfFeatures = totalNumberOfFeatures;
	}
	public int getTotalNumberOfScenarios() {
		return totalNumberOfScenarios;
	}
	public void setTotalNumberOfScenarios(int totalNumberOfScenarios) {
		this.totalNumberOfScenarios = totalNumberOfScenarios;
	}
	public int getTotalNumberOfSteps() {
		return totalNumberOfSteps;
	}
	public void setTotalNumberOfSteps(int totalNumberOfSteps) {
		this.totalNumberOfSteps = totalNumberOfSteps;
	}
	public int getTotalNumberPassingSteps() {
		return totalNumberPassingSteps;
	}
	public void setTotalNumberPassingSteps(int totalNumberPassingSteps) {
		this.totalNumberPassingSteps = totalNumberPassingSteps;
	}
	public int getTotalNumberFailingSteps() {
		return totalNumberFailingSteps;
	}
	public void setTotalNumberFailingSteps(int totalNumberFailingSteps) {
		this.totalNumberFailingSteps = totalNumberFailingSteps;
	}
	public int getTotalNumberSkippedSteps() {
		return totalNumberSkippedSteps;
	}
	public void setTotalNumberSkippedSteps(int totalNumberSkippedSteps) {
		this.totalNumberSkippedSteps = totalNumberSkippedSteps;
	}
	public int getTotalNumberPendingSteps() {
		return totalNumberPendingSteps;
	}
	public void setTotalNumberPendingSteps(int totalNumberPendingSteps) {
		this.totalNumberPendingSteps = totalNumberPendingSteps;
	}
	public int getTotalScenariosPassed() {
		return totalScenariosPassed;
	}
	public void setTotalScenariosPassed(int totalScenariosPassed) {
		this.totalScenariosPassed = totalScenariosPassed;
	}
	public int getTotalScenariosFailed() {
		return totalScenariosFailed;
	}
	public void setTotalScenariosFailed(int totalScenariosFailed) {
		this.totalScenariosFailed = totalScenariosFailed;
	}
	 
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public List<FeatureDetialsEntity> getFeatures() {
		
		if (features == null) {
			features = new ArrayList<FeatureDetialsEntity>();
		}
		return features;
	}
	public void setFeatures(List<FeatureDetialsEntity> features) {
		this.features = features;
	}
	
    
    
    
}
