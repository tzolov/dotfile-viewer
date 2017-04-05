# dotfile-viewer
Self-contained solution to render Graphviz files on the web. A single jar that embeds Tomcat. 
Internally the viewer uses [Spring Boot](http://projects.spring.io/spring-boot/) and [Polymer](https://www.polymer-project.org/) and the custom [vis-js](http://tzolov.github.io/viz-js/components/viz-js) polymer-element.

[Live demo1](http://dotfile-viewer.cfapps.io/), [Live demo2](http://dotfile-viewer.cfapps.io/responsive.html)

## Usage

Build the project: 

```
    mvn clean install
```

and run the embedded Tomcat server with the dotfile viewer application:

```
    java -jar target/dotfile-viewer-0.0.2-SNAPSHOT.jar --dotfiles.pattern=<Ant-style dotfiles location path pattern>
```

*Pre-build jar is available at: [dotfile-viewer-0.0.3-SNAPSHOT.jar](https://www.dropbox.com/s/isc340bhozb1piu/dotfile-viewer-0.0.3-SNAPSHOT.jar?dl=0)*                                                             

`--dotfiles.pattern` - ANT-style path to the location containing the dot files. 
Standardized prefixes are used to indicate the URL type of the source. This includes `file:` for accessing filesystem paths, `http:` for accessing resources via the HTTP protocol, `ftp:` for accessing resources via FTP, et. It also recognizes the special prefix `classpath:` for dotfiles which should be obtained from the classpath.

For example set a local file folder: `--dotfiles.pattern=file:/<path to the dotfiles folder>/**`. Note the `file:` prefix and the `**` wild-card. Additional [pattern conventions](http://docs.spring.io/spring-framework/docs/2.5.x/api/org/springframework/core/io/support/PathMatchingResourcePatternResolver.html).  

`--server.port=<your port>` - optional parameter to change the default (8080) Tomcat server port.

For examples run the viewer to visualize the sample dot files in `src/test/resources`:
```bash
java -jar ./target/dotfile-viewer-0.0.3-SNAPSHOT.jar --dotfiles.pattern=file:./src/test/resources/**
```

Then open [http://localhost:8080](http://localhost:8080) 

<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.22.47%20PM.png" alt="basic view" width="300"></img>

or an alternative ([responsive](http://en.wikipedia.org/wiki/Responsive_web_design)) layout: [http://localhost:8080/responsive.html](http://localhost:8080/responsive.html)

<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.23.39%20PM.png" alt="responsive view 1" width="200"></img>
<img src="https://raw.githubusercontent.com/tzolov/dotfile-viewer/master/doc/Screen%20Shot%202015-01-07%20at%205.24.25%20PM.png" alt="responsive view 3" width="130"></img>

The filter control allows to filter in only the desired files.



