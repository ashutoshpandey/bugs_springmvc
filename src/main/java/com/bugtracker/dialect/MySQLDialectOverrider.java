package com.bugtracker.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDialectOverrider extends MySQLDialect{
	
    public MySQLDialectOverrider() {
        super();
        registerHibernateType(Types.NVARCHAR, StandardBasicTypes.STRING.getName());
        registerHibernateType(Types.LONGVARCHAR, StandardBasicTypes.TEXT.getName());
    }
}