package pjatk.finalproject.model;

import java.time.LocalDate;
import java.util.UUID;

import static pjatk.finalproject.model.Utils.checkCorrectnessOfNumericalValueGreaterThanZero;
import static pjatk.finalproject.model.Utils.checkIfDateIsNotNull;

public class Employment {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double salary;
    private Employee employee;
    private CompanyBranch companyBranch;

    public Employment(LocalDate startDate, LocalDate endDate, double salary, Employee employee, CompanyBranch companyBranch) {
        setId();
        setStartDate(startDate);
        setEndDate(endDate);
        setSalary(salary);
        setEmployee(employee);
        setCompanyBranch(companyBranch);
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        checkIfDateIsNotNull(startDate, "Start date of rental");
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        checkIfDateIsNotNull(endDate, "End date of rental");
        if (endDate.isBefore(this.getStartDate())) {
            throw new IllegalArgumentException("End date of rental cannot be before start date.");
        }
        this.endDate = endDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        checkCorrectnessOfNumericalValueGreaterThanZero(salary, "Salary");
        this.salary = salary;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (this.employee == null && employee != null) {
            // new employee - no employee previously assigned
            this.employee = employee;
            employee.addEmployment(this);

        } else if (this.employee != null && employee == null) {
            // removing the employee
            Employee tmp = this.employee;
            this.employee = null;
            tmp.removeEmployment(this);

        } else if (this.employee != null && employee != null) {
            // changing the employee
            Employee tmp = this.employee;
            this.employee = null;
            tmp.removeEmployment(this);

            this.employee = employee;
            employee.addEmployment(this);
        }
    }

    public CompanyBranch getCompanyBranch() {
        return companyBranch;
    }

    public void setCompanyBranch(CompanyBranch companyBranch) {
        this.companyBranch = companyBranch;
    }
}
