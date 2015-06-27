dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/honeyalarmdev?useUnicode=yes&characterEncoding=UTF-8"
            username = "honeyalarmdev"
            password = "honeyalarmpw"
        }
        hibernate {
            show_sql = true
        }
    }
    test {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost/honeyalarmdev?useUnicode=yes&characterEncoding=UTF-8"
            username = "honeyalarmdev"
            password = "honeyalarmpw"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost/honeyalarmprod?useUnicode=yes&characterEncoding=UTF-8"
            username = "honeyalarmprod"
            password = "honeyalarmpw"
        }
    }
}