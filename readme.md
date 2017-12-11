# Vain Ruling

# 1. Vain Ruling / ENISo info
Vain Ruling is a Open Source Web App Framework for building Plugin Based Extensible Web Application.
A custom application that uses extensively VR is ENISo info which is an Open Source academic platform.

Author  : Taha BEN SALAH


# 2. Eniso.info

## 2.1. How to compile
To compile eniso.info the following projects must be compiled in this order :
1.  **vr-plugin** is the parent plugin project for all vr plugins (mvn install)
2.  **vain-ruling** is the parent project for all VR plugins (mvn install)
2.  **eniso-info** is the eniso.info parent project for all eniso.info academic platform  (mvn install)


## 2.2. configuration :
### 2.2.1. Default configuration :
ENSo info uses a default java derby database but can be configured with other rdbms such as mysql. 
You have nothing to configure to use default embedded derby configuration. If one needs to configure
db access a file named upa.xml should be configured accordingly. The file is located at :

${user.home}/workspace/eniso.info/config/upa.xml

### 2.2.2 derby configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<upa xmlns="http://github.com/thevpc/upa/upa-1.0.xsd" version="1.0">
    <persistenceUnit name="main">
        <connection>
            <connectionString>
                derby:embedded//enisoinfodb;structure=create;userName=enisoinfouser;password='mypassword';
            </connectionString>
        </connection>
    </persistenceUnit>
</upa>
```

### 2.2.3. mysql configuration

For mysql, a valid database must be configured first. All Tables will be generated by UPA.

#### Check mysql installation
```bash
systemctl status mysql
systemctl enable mysql
systemctl start mysql

mysqladmin -u root password "<YOUR PASSWORD>"
# or (if you need to change password)
mysqladmin -u root -p'oldpassword' password "<YOUR PASSWORD>"

mysql -u root -p"<YOUR PASSWORD>"
```


#### create database and user
```sql
CREATE DATABASE enisoinfodb DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
CREATE USER 'enisoinfouser'@'localhost' IDENTIFIED BY "<YOUR PASSWORD>";
GRANT ALL PRIVILEGES ON enisoinfodb . * TO 'enisoinfouser'@'localhost';
FLUSH PRIVILEGES;
```

#### edit upa.xml file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<upa xmlns="http://github.com/thevpc/upa/upa-1.0.xsd" version="1.0">
    <persistenceUnit name="main">
        <connection>
            <connectionString>
                mysql//locahost/enisoinfodb;structure=create;userName=enisoinfouser;password='mypassword';
            </connectionString>
        </connection>
    </persistenceUnit>
</upa>
```

### 2.2.4. advanced mysql configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<upa xmlns="http://github.com/thevpc/upa/upa-1.0.xsd" version="1.0">
    <persistenceUnit name="main">
        <connection>
            <connectionString>
                mysql://localhost/enisoinfodb;
                structure=create;
                userName=enisoinfouser;
                password='mypassword';
                pool=true;
                poolMaxSize=10;
                monitor=javamelody
            </connectionString>
        </connection>
    </persistenceUnit>
</upa>
```

### 2.3. Staring VR

#### 2.3.1. Deploying
**eniso-info-web** project is the war application to run on any Tomcat Web Container. No custom webserver configuration is needed.

#### 2.3.2. Default login/password
login    : admin
password : admin


## 3. Developing your own plugin

### 3.1 compile base plugin
You first have to compile :
* vr-plugin
* vr-plugin-archetype

### 3.2 Generate plugin skeleton
In the command line, under **apps/eniso-info/plugins** directory type the following :
```sh
mvn archetype:generate -DarchetypeGroupId=net.vpc.app.vain-ruling.core -DarchetypeArtifactId=vr-sample-plugin-archetype
```
You should have maven command (mvn) in your PATH env. If not, you should use the full path of maven. For instance if maven 
is installed under **/path/to/maven** then the command should become
```sh
/path/to/maven/bin/mvn archetype:generate -DarchetypeGroupId=net.vpc.app.vain-ruling.core -DarchetypeArtifactId=vr-sample-plugin-archetype
```

You will be asked for your project groupId (you may type : mycompany), your artifactId (you may type : myplugin), and your version (you may type 1.0).

myplugin/myplugin-service and myplugin/myplugin-web will be created for you.

### 3.2 Add plugin to build process
In the root directory of eniso.info, open pom.xml (aka apps/eniso-info/pom.xml) and add these lines
```xml
        <module>plugins/myplugin/myplugin-service</module>
        <module>plugins/myplugin/myplugin-web</module>
```

### 3.3 Add plugin to app dependency
Open apps/eniso-info/eniso-info-service/pom.xml and add these lines in the **dependencies** tag
```xml
        <dependency>
            <groupId>mycompany</groupId>
            <artifactId>myplugin-service</artifactId>
            <version>1.0</version>
        </dependency>
```
Then open apps/eniso-info/eniso-info-web/pom.xml and add these lines in the **dependencies** tag
```xml
        <dependency>
            <groupId>mycompany</groupId>
            <artifactId>myplugin-web</artifactId>
            <version>1.0</version>
        </dependency>
```

### 3.4 Compile and Deploy
After compiling eniso.info, the plugin should be loaded

### 3.5 Customize your plugins
We will see how to change Plugin description, add new Entity and add new Page





