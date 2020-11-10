# Community Levels
http://communitylevels.online

# Setup
* Add the `inject-communitylevels-db-password` profile to your maven `.m2/settings.xml`.
* Run `[dev] write-project-properties.launch` to build the `deploy.properties`.


**settings.xml**
```
<profile>
   <id>inject-communitylevels-db-password</id>
   <activation>
      <activeByDefault>true</activeByDefault>
   </activation>
   <properties>
      <communitylevels.db.password>*password*</communitylevels.db.password>
      <communitylevels.wz.mail>*mail*</communitylevels.wz.mail>
      <communitylevels.wz.api.token>*token*</communitylevels.wz.api.token>
   </properties>
</profile>
```

# Logs
```
/var/log/tomcat8/
```



