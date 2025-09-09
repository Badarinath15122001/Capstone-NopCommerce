Automation Project Skeleton for demo.nopcommerce.com

Structure:
- pom.xml
- testng.xml
- src/main/java: page objects and utils
- src/test/java: step definitions, runners, listeners
- src/test/resources/features: cucumber feature files
- testdata/config.properties: configuration

How to import into Eclipse:
1. Download and unzip the provided ZIP.
2. In Eclipse: File -> Import -> Maven -> Existing Maven Projects -> select the unzipped folder -> Finish.
3. Right-click project -> Maven -> Update Project to download dependencies.
4. Install TestNG plugin in Eclipse (if not present) via Help -> Eclipse Marketplace -> TestNG.
5. Run the suite: Right-click testng.xml -> Run As -> TestNG Suite.
6. Reports will be generated in the reports/ folder (ExtentReport.html and screenshots).

Notes:
- Update testdata/config.properties for your environment (browser, base.url).
- Adjust locators if demo site structure changes.
- This skeleton includes many files; extend assertions and waits for stability.
