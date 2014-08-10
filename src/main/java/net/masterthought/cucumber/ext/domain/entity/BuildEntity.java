package net.masterthought.cucumber.ext.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
@Entity
@Table(name = "BUILD_DETAILS")

@NamedQuery (name = "BuildEntity.findBuild" , 
			query = "select e.id,  max(e.createDate), e.projectName,  e.buildNumber  from BuildEntity e  ")
public class BuildEntity  implements Serializable {

	
	  	@Id 
	  	@GeneratedValue
	    private Long id;
	  	
	  	@Column( name = "EXECUTION_DATE" , nullable = false)
		@Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
		private DateTime createDate = new DateTime(DateTimeZone.UTC);
	  	
	  	
	    @Column (name = "PROJECT_NAME" , length = 10000)
	    private String projectName;
	    
	    @Lob
	    @Column (name = "RAW_DATA")
	    private String data;
	    
	    @Column (name = "BUILD_NUMBER" ,length = 500)
	    private String buildNumber;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public DateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(DateTime createDate) {
			this.createDate = createDate;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

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

		public BuildEntity(String projectName,
				 String buildNumber , String data) {
			super();
			this.projectName = projectName;
			this.data = data;
			this.buildNumber = buildNumber;
		}

		public BuildEntity() {
			super();
		}
		
}
