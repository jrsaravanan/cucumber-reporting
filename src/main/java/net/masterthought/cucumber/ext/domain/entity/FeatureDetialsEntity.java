package net.masterthought.cucumber.ext.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
@Entity
@Table(name = "FEATURE_DETAILS")
public class FeatureDetialsEntity  implements Serializable {

	
	  	@Id 
	  	@GeneratedValue
	    private Long id;
	  	
	  	@Column( name = "EXECUTION_DATE" , nullable = false)
		@Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
		private DateTime createDate = new DateTime(DateTimeZone.UTC);
	  	
	  	
	    @Column (name = "PROJECT_NAME" , length = 10000)
	    private String projectName;
	
	    @Column (name = "FEATURE_NAME" , length = 10000)
	    private String name;
	    
	    @Column (name = "TOTAL_SCENARIO")
	    private int totalScenario;
	    
	    @Column (name = "SCENARIO_PASSED")
	    private int scenarioPassed;
	    
	    @Column (name = "SCENARIO_FAILED")
	    private int scenarioFailed;
	    
	    @Column (name = "TOTAL_STEPS")
	    private int totalSteps;
	    
	    @Column (name = "STEPS_PASSED")
	    private int stepPassed;
	    
	    @Column (name = "STEPS_FAILED")
	    private int stepFailed;
	    
	    @Column (name = "STEPS_PENDING")
	    private int stepPending;
	    
	    @Column (name = "STEPS_MISSING")
	    private int stepMissing;
	    
	    @Column (name = "STEPS_UNDEFINED")
	    private int stepUndefined;
	    
	    @Column (name = "STEPS_SKIPPED")
	    private int stepSkipped;
	    
	    @Column (name = "DURATION" , length = 5000)
	    private String duration;
	    
	    @Column (name ="STATUS")
	    private String status;
	    
	    @Column (name = "DESCRIPTION" , length = 10000)
	    private String description;
	    
	    @Lob
	    @Column (name = "RAW_DATA")
	    private String data;
	    
	    private String buildNumber;

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getBuildNumber() {
			return buildNumber;
		}

		public void setBuildNumber(String buildNumber) {
			this.buildNumber = buildNumber;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getScenarioPassed() {
			return scenarioPassed;
		}

		public void setScenarioPassed(int scenarioPassed) {
			this.scenarioPassed = scenarioPassed;
		}

		public int getScenarioFailed() {
			return scenarioFailed;
		}

		public void setScenarioFailed(int scenarioFailed) {
			this.scenarioFailed = scenarioFailed;
		}

		public int getStepPassed() {
			return stepPassed;
		}

		public void setStepPassed(int stepPassed) {
			this.stepPassed = stepPassed;
		}

		public int getStepFailed() {
			return stepFailed;
		}

		public void setStepFailed(int stepFailed) {
			this.stepFailed = stepFailed;
		}

		public DateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(DateTime createDate) {
			this.createDate = createDate;
		}

		public int getStepPending() {
			return stepPending;
		}

		public void setStepPending(int stepPending) {
			this.stepPending = stepPending;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getTotalScenario() {
			return totalScenario;
		}

		public void setTotalScenario(int totalScenario) {
			this.totalScenario = totalScenario;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public int getTotalSteps() {
			return totalSteps;
		}

		public void setTotalSteps(int totalSteps) {
			this.totalSteps = totalSteps;
		}

		public int getStepMissing() {
			return stepMissing;
		}

		public void setStepMissing(int stepMissing) {
			this.stepMissing = stepMissing;
		}

		public int getStepUndefined() {
			return stepUndefined;
		}

		public void setStepUndefined(int stepUndefined) {
			this.stepUndefined = stepUndefined;
		}

		public int getStepSkipped() {
			return stepSkipped;
		}

		public void setStepSkipped(int stepSkipped) {
			this.stepSkipped = stepSkipped;
		}

		
		
}
