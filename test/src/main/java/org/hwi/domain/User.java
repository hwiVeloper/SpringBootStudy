package org.hwi.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Table(name = "user")
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private String id;
		private String pwd;
		private String level;
		
		@CreationTimestamp
		private Timestamp regdate;
}