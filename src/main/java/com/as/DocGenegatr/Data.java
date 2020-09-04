package com.as.DocGenegatr;

public class Data {
	  private String empNumber;
	  private String empName;
	  private String joiningDate;
	  private String salary;
	  private String deptNo;
	  private String managerId;
	  public Data(String empNumber, String empName,String joiningDate, String salary, String deptNo, String managerId) {
	    super();
	    this.empNumber = empNumber;
	    this.empName = empName;
	    this.joiningDate = joiningDate;
	    this.salary = salary;
	    this.deptNo = deptNo;
	    this.managerId = managerId;
	  }
	  public String getEmpNumber() {
	    return empNumber;
	  }
	  public void setEmpNumber(String empNumber) {
	    this.empNumber = empNumber;
	  }
	  
	  public String getEmpName() {
	    return empName;
	  }
	  public void setEmpName(String empName) {
	    this.empName = empName;
	  }
	  public String getJoiningDate() {
	    return joiningDate;
	  }
	  public void setJoiningDate(String joiningDate) {
	    this.joiningDate = joiningDate;
	  }
	  public String getSalary() {
	    return salary;
	  }
	  public void setSalary(String salary) {
	    this.salary = salary;
	  }
	  public String getDeptNo() {
	    return deptNo;
	  }
	  public void setDeptNo(String deptNo) {
	    this.deptNo = deptNo;
	  }
	  public String getManagerId() {
	    return managerId;
	  }
	  public void setManagerId(String managerId) {
	    this.managerId = managerId;
	  }
	}