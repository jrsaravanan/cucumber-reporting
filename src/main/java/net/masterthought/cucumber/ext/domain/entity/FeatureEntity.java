package net.masterthought.cucumber.ext.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
@Entity
@Table(name = "FEATURE")
public class FeatureEntity  implements Serializable {

	
	  	@Id  
	  	@GeneratedValue (strategy = GenerationType.AUTO)
	  	@Column (name = "FEATURE_ID")
	    private Long id;
	  	
	  	@Column( name = "FEATURE_DATE" , nullable = false)
		@Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
		private DateTime createDate = new DateTime(DateTimeZone.UTC);
	
	  	@Column (name ="PROJECT_NAME")
	  	private String projectName;
	  	
	    @Column (name = "FEATURE_NAME", length=10000)
	    private String name;
	    
	    @Column (name = "DESCRIPTION" , length=10000)
	    private String description;
	    
	    @OneToMany (cascade = CascadeType.ALL , mappedBy ="feature")
	    private Set<ElementEntity> elements = new HashSet<ElementEntity>();

	    
	    public FeatureEntity() {
			
		}

		public FeatureEntity(Long id, DateTime createDate, String name,
				String description, Set<ElementEntity> elements) {
			super();
			this.id = id;
			this.createDate = createDate;
			this.name = name;
			this.description = description;
			this.elements = elements;
		}
		
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

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Set<ElementEntity> getElements() {
			return elements;
		}

		public void setElements(Set<ElementEntity> elements) {
			this.elements = elements;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

}
