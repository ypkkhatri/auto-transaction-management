package com.yog.transaction.ejbs.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LOG")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "METHOD", nullable = false, length = 255)
    private String method;

    @NotNull
    @Column(name = "CLASS", nullable = false, length = 255)
    private String methodClass;

    @NotNull
    @Column(name = "DATE_TIME", nullable = false, length = 255)
    private String dateTime;

    @NotNull
    @Column(name = "PARAMETERS", nullable = false, columnDefinition = "TEXT")
    private String parameters;

    public Log() {
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodClass() {
        return methodClass;
    }

    public void setMethodClass(String methodClass) {
        this.methodClass = methodClass;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

}
