# Community Levels
http://communitylevels.online/ui

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
      <communitylevels.db.password>*add pw here*</communitylevels.db.password>
   </properties>
</profile>
```





