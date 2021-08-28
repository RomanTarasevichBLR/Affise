
# Affire Test-Automation Demo task

## Environment Variables

**NOTE!!**

***API Key value was hardcoded to request specification header, but on real project 
API Key will be setted via environment variables.***
 
`Auth=API-Key`

`Key=456505a43....`

  
## Run Locally

Clone the project

```bash
  git clone https://github.com/RomanTarasevichBLR/Affise.git
```

Go to the project directory

```bash
  cd yourProjectLocation
```
Run the test via Maven command:

```bash
  mvn clean test
```


  After test will be finish check saved screenshot:
```bash
  yourProjectLocation/AffiseTest/screenshots-results/
```