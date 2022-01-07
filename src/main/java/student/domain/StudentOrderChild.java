package student.domain;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jc_student_child")
public class StudentOrderChild{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="student_child_id")
	private Long studentChildId;
	@ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinColumn(name="student_order_id")
	private StudentOrder studentOrder;

	@AssociationOverrides({
		@AssociationOverride(name="address.street", joinColumns =@JoinColumn(name="c_street_code")),
		@AssociationOverride(name="registerOffice.c_register_office_id", joinColumns =@JoinColumn(name="c_street_code"))
	})
	@AttributeOverrides({
		@AttributeOverride(name="surName", column=@Column(name="c_sur_name")),
		@AttributeOverride(name="givenName", column=@Column(name="c_given_name")),
		@AttributeOverride(name="patronymic", column=@Column(name="c_patronymic")),
		@AttributeOverride(name="dateOfBirth", column=@Column(name="c_date_of_birth")),
		@AttributeOverride(name="address.postCode", column=@Column(name="c_post_index")),
		@AttributeOverride(name="address.building", column=@Column(name="c_building")),
		@AttributeOverride(name="address.extension", column=@Column(name="c_extension")),
		@AttributeOverride(name="address.apartment", column=@Column(name="c_apartment")),
		@AttributeOverride(name="childCertificate", column=@Column(name="c_certificate_number")),
		@AttributeOverride(name="certificateDate", column=@Column(name="c_certificate_date")),
		@AttributeOverride(name="checkChildCityRegister", column=@Column(name="c_check_child_city_register")),
		@AttributeOverride(name="checkChildRegister", column=@Column(name="c_check_child_register"))
		
	})
	@Embedded
	private Child child;
	private String emailAdd;
	private String emailEdit;
	
	
	
	
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	public String getEmailEdit() {
		return emailEdit;
	}
	public void setEmailEdit(String emailEdit) {
		this.emailEdit = emailEdit;
	}
	public Long getStudentChildId() {
		return studentChildId;
	}
	public void setStudentChildId(Long studentChildId) {
		this.studentChildId = studentChildId;
	}
	public StudentOrder getStudentOrder() {
		return studentOrder;
	}
	public void setStudentOrder(StudentOrder studentOrder) {
		this.studentOrder = studentOrder;
	}
	public Child getChild() {
		return child;
	}
	public void setChild(Child child) {
		this.child = child;
	}

	
	
	
	
}
