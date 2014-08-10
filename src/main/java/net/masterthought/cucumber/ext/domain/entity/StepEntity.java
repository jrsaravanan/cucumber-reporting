package net.masterthought.cucumber.ext.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "STEP")
public class StepEntity  implements Serializable {

	
	  	@Id 
	  	@GeneratedValue (strategy =GenerationType.AUTO)
	  	@Column (name = "STEP_ID")
	    private Long id;
	  	
	    @Column (name = "STEP_NAME" , length=10000)
	    private String name;
	    
	    @Column (name = "STEP_DESCRIPTION" , length=10000)
	    private String description;
	    
	    @Column (name = "STEP_STATUS")
	    private String status;
	    
	    @Column (name = "ERROR_MESSAGE" , length=10000)
	    private String errorMessage;
	    
	    @Column (name = "STEP_DURATION")
	    private Long duration;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="ELEMENT_ID")
	    private ElementEntity element;

	   
	    public StepEntity() {
	    	
	    }
	    
		public StepEntity(String name, String description,
				String status, String errorMessage, Long duration,
				ElementEntity element) {
			super();
			this.name = name;
			this.description = description;
			this.status = status;
			this.errorMessage = errorMessage;
			this.duration = duration;
			this.element = element;
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Long getDuration() {
			return duration;
		}

		public void setDuration(Long duration) {
			this.duration = duration;
		}

		public ElementEntity getElement() {
			return element;
		}

		public void setElement(ElementEntity element) {
			this.element = element;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		

}
