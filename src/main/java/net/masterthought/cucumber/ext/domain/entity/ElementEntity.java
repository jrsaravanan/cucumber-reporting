package net.masterthought.cucumber.ext.domain.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
@Entity
@Table(name = "ELEMENT")
public class ElementEntity  implements Serializable {

	
	  	@Id 
	  	@GeneratedValue (strategy =GenerationType.AUTO)
	  	@Column (name = "ELEMENT_ID")
	    private Long id;
	  	
	  	@Column( name = "ELEMENT_DATE" , nullable = false)
		@Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
		private DateTime createDate = new DateTime(DateTimeZone.UTC);
	
	    @Column (name = "ELEMENT_NAME" , length=5000)
	    private String name;
	    
	    @Column (name = "ELEMENT_DESCRIPTION" , length=5000)
	    private String description;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="FEATURE_ID")
	    private FeatureEntity feature;
	    
	    @OneToMany (cascade = CascadeType.ALL , mappedBy ="element")
	    private Set<StepEntity> steps = new HashSet<StepEntity>();

	    public ElementEntity() {
	    	
	    }
	    
		public ElementEntity(String name,String description, FeatureEntity feature) {
			super();
			this.name = name;
			this.description = description;
			this.feature = feature;
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

		public Set<StepEntity> getSteps() {
			return steps;
		}

		public void setSteps(Set<StepEntity> steps) {
			this.steps = steps;
		}

}
