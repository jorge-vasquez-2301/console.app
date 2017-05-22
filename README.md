# Pet management system console client application

<ol>
    <li><a href="#design">Design choices</a></li>
    <li><a href="#requirements">Requirements</a></li>
    <li><a href="#instructions">Running instructions</a></li>
</ol>

**<a name="design"><h2>Design choices</h2></a>**
<ul>
    <li> This is a RESTful client for the Pet management system application</li>
    <li><b>SBT</b> is used as the build and dependencies management tool for this project, like the pet management system application</li>
    <li><b>JUnit</b> was the chosen framework for testing</li>
    <li>The application always creates pets from the CSV file provided as input, such as data.csv (located in src/main/resources), so if it is run multiple times, data is inserted multiple times in the pet management system (with different pet id's) </li>
</ul>

**<a name="requirements"><h2>Requirements</h2></a>**
<ul>
    <li>JDK 8, you can get it from <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html" target="_blank">here</a></li>
    <li>SBT, you can find installation instructions for Windows, Linux or Mac <a href="http://www.scala-sbt.org/0.13/docs/Setup.html" target="_blank">here</a></li>
</ul>

**<a name="instructions"><h2>Running instructions</h2></a>**
<ul>
    <li>For configuring the server address and port use the console.properties file, located in src/main/resources</li>
    <li>For running the application, just issue a command like <code>sbt "run src/main/resources/data.csv name=Spike"</code> or <code>sbt "run src/main/resources/data.csv type=dog"</code> or <code>sbt "run src/main/resources/data.csv type=dog gender=male"</code> or <code>sbt "run src/main/resources/data.csv delete=1"</code> in the project's root directory, where the build.sbt file resides (if sbt is not included in system path, execute <code>/full/path/to/sbt "run [parameters]"</code>)</li>
    <li>The first time the application runs, SBT will download automatically all the dependencies</li>
</ul>
