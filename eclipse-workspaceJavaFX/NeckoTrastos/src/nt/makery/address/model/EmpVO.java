package nt.makery.address.model;

public class EmpVO {
	/*** ATRIBUTS ***/
		private int empno;
		private String ename;
		private String job;
		private String mgr;
		private String hiredate;
		private String salary;
		private String comm;
		private String deptno;
	
	/*** default CONSTRUCTOR ***/
		public EmpVO() {
		
		}
	/*** CONSTRUCTORS ***/
		
	public EmpVO(int empno, String ename) {
		super();
		this.empno = empno;
		this.ename = ename;
	}
	
	public EmpVO(int empno, String ename, String job, String mgr, String hiredate, String salary, String comm, String deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.salary = salary;
		this.comm = comm;
		this.deptno = deptno;
	}
	
	/*** setters & getters ***/
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMgr() {
		return mgr;
	}

	public void setMgr(String mgr) {
		this.mgr = mgr;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
		
		
}
