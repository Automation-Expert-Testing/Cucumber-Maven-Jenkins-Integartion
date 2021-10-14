pipeline {
agent any
  stages {
  stage('Parameters'){
                steps {
                    script {
                    properties([
                        parameters([
                        [$class: 'ChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Testing Environment from the Dropdown List',
                                    filterLength: 1,
                                    filterable: false,
                                    name: 'TestingEnvironment',
                                    script: [
                                        $class: 'GroovyScript',
                                        fallbackScript: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Could not get The Testing Environment']"
                                        ],
                                        script: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['QA','DEV','STG','UAT','Production']"
                                        ]
                                    ]
                                ],
                        [$class: 'ChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the test suit using the corresponding Cucumber Tag from the Dropdown List',
                                    filterLength: 1,
                                    filterable: false,
                                    name: 'CucumberTag',
                                    script: [
                                        $class: 'GroovyScript',
                                        fallbackScript: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Could not get The Cucumber Tag']"
                                        ],
                                        script: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['@amazon','@youtube','@google','@regression','@sanity','@smoke','@loadTesting']"
                                        ]
                                    ]
                                ],
                                [$class: 'ChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the if Use Cloud Environment from the Dropdown List',
                                    filterLength: 1,
                                    filterable: false,
                                    name: 'UseCloudEnv',
                                    script: [
                                        $class: 'GroovyScript',
                                        fallbackScript: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Could not get the use of Cloud Environment status']"
                                        ],
                                        script: [
                                      
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['False','True']"
                                        ]
                                    ]
                                ],
                                [$class: 'CascadeChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Cloud Environment Name from the Dropdown List',
                                    name: 'CloudEnvName',
                                    referencedParameters: 'UseCloudEnv',
                                    script:
                                        [$class: 'GroovyScript',
                                        fallbackScript: [
                                                classpath: [],
                                                sandbox: false,
                                                script: "return['Could not get Cloud Environment Name']"
                                                ],
                                        script: [
                                                classpath: [],
                                                sandbox: false,
                                                script: '''
                                                if (UseCloudEnv.equals("True")){
                                                    return["Browserstack","Saucelab"]
                                                }
                                                '''
                                            ]
                                    ]
                              ],
                                [$class: 'ChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Operating System from the Dropdown List',
                                    filterLength: 1,
                                    filterable: false,
                                    name: 'Os',
                                    script: [
                                        $class: 'GroovyScript',
                                        fallbackScript: [
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Could not get The Operating System']"
                                        ],
                                        script: [
                                      
                                            classpath: [],
                                            sandbox: false,
                                            script:
                                                "return['Mac','Windows','Linux']"
                                        ]
                                    ]
                                ],
                                [$class: 'CascadeChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Operating System Version from the Dropdown List',
                                    name: 'Os_Version',
                                    referencedParameters: 'Os',
                                    script:
                                        [$class: 'GroovyScript',
                                        fallbackScript: [
                                                classpath: [],
                                                sandbox: false,
                                                script: "return['Could not get Operating System Version']"
                                                ],
                                        script: [
                                                classpath: [],
                                                sandbox: false,
                                                script: '''
                                                if (Os.equals("Mac")){
                                                    return["macOS 12(Monterey)", "macOS 11(Big Sur)", "macOS 10.15(Catalina)","macOS 10.14(Mojave)","macOS 10.13(High Sierra)","macOS 10.12(Sierra)","OS X 10.11(El Capitan)"]
                                                }
                                                else if(Os.equals("Windows")){
                                                    return["Windows 11", "Windows 10", "Windows 7","Windows 8","Windows XP","Windows Vista","Windows 2000","Windows ME","Windows 98"]
                                                }
                                                else if(Os.equals("Linux")){
                                                    return["Debian Linux", "Gentoo Linux", "Ubuntu Linux","Linux Mint Desktop","RHEL Linux Distribution","CentOS Linux Distribution","Fedora Linux Distribution","Kali Linux Distribution"]
                                                }
                                                '''
                                            ]
                                    ]
                              ],
                            
                              [$class: 'CascadeChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Browser Name from the Dropdown List',
                                    name: 'Browser_Name',
                                    referencedParameters: 'Os,Os_Version',
                                    script:
                                        [$class: 'GroovyScript',
                                        fallbackScript: [
                                                classpath: [],
                                                sandbox: false,
                                                script: "return['Could not get Browser Name']"
                                                ],
                                        script: [
                                                classpath: [],
                                                sandbox: false,
                                                script: '''
                                                if (Os.equals("Mac")){
                                                    return["Chrome","Edge","Safari","Opera","Firefox"]
                                                }
                                                else if(Os.equals("Windows")){
                                                    return["Chrome","Internet Explorer","Edge","Opera","Firefox"]
                                                }
                                                else if(Os.equals("Linux")){
                                                    return["Chrome","Edge","Safari","Opera","Firefox"]
                                                }
                                                '''
                                            ]
                                    ]

                            ],
                            [$class: 'CascadeChoiceParameter',
                                    choiceType: 'PT_SINGLE_SELECT',
                                    description: 'Select the Browser Version from the Dropdown List',
                                    name: 'Browser_Version',
                                    referencedParameters: 'Os,Os_Version,Browser_Name',
                                    script:
                                        [$class: 'GroovyScript',
                                        fallbackScript: [
                                                classpath: [],
                                                sandbox: false,
                                                script: "return['Could not get Browser version']"
                                                ],
                                        script: [
                                                classpath: [],
                                                sandbox: false,
                                                script: '''
                                                if (Os.equals("Mac")){
                                                    if(Browser_Name.equals("Chrome")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Edge")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Firefox")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Safari")){
                                                    return["15","14","13","12","11","10","9"]
                                                    }else if(Browser_Name.equals("Opera")){
                                                    return["74","73","72","71","70","69","68"]}
                                                    
                                                }
                                                else if(Os.equals("Windows")){
                                                    if(Browser_Name.equals("Chrome")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Edge")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Firefox")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Internet Explorer")){
                                                    return["11","10","9","8","7"]
                                                    }else if(Browser_Name.equals("Opera")){
                                                    return["74","73","72","71","70","69","68"]}
                                                }
                                                else if(Os.equals("Linux")){
                                                    if(Browser_Name.equals("Chrome")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Edge")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Firefox")){
                                                    return["97","96","95","94","93","92","91","90","89","88","87"]
                                                    }else if(Browser_Name.equals("Safari")){
                                                    return["15","14","13","12","11","10","9"]
                                                    }else if(Browser_Name.equals("Opera")){
                                                    return["74","73","72","71","70","69","68"]}
                                                }
                                                '''
                                            ]
                                     ]

                                 ],
                            
                            
                            string(defaultValue: 'src/test/resources/secret.properties', description: 'Secret Properties Path', name: 'SecretFilePath',trim: true),

                            string(defaultValue: '4', description: 'Implicitly wait time', name: 'ImplicitlyWaitTime',trim: true),

                            string(defaultValue: 'automationexpert80@gmail.com', description: 'email for notifications', name: 'notification_email',trim: true)
                            ])
                        ])
                    }
                }
            }
         stage("Load Properties in to configuration file"){
                 steps{
            script {
                def props = """

TestingEnvironment =${params.TestingEnvironment}
UseCloudEnv =${params.UseCloudEnv}
CloudEnvName =${params.CloudEnvName}
Os =${Os}
Os_version =${params.Os_Version}
BrowserName =${params.Browser_Name}
BrowserVersion =${params.Browser_Version}
ImplicitlyWaitTime =${params.ImplicitlyWaitTime}
SecretFilePath =${params.SecretFilePath}

                """
                writeFile file: "config.properties", text: props
                def str =  readFile file: "config.properties"
                echo str
            }
         }
      }
stage('Running the test suit'){

steps{
            script {
             def os = params.Os
             if(os=="Windows"){
             bat(/mvn install -Dcucumber.filter.tags=${params.CucumberTag}/)
             }else
              {sh "mvn install -Dcucumber.filter.tags=${params.CucumberTag}"}
              
              }
     }
   }
}
post {
always {
echo "Test succeeded"
emailext attachmentsPattern: '**/*.html, **/*.pdf',
to: "${params.notification_email}",
subject: "Status and reports of pipeline: ${currentBuild.fullDisplayName}",
           body:"""
           <p style="background-color:powderblue;">EXECUTED: Job <b> ${env.JOB_NAME}:${env.BUILD_NUMBER}
           </b></p>
           <p style="color:DodgerBlue;">View console output at "<a href="${env.BUILD_URL}">
           ${env.JOB_NAME}:${env.BUILD_NUMBER}</a>"</p>
           <p style="color:DodgerBlue;"><h1>The Testing Environment is     : ${params.TestingEnvironment}</h1></p>
           <p style="color:DodgerBlue;"><i>Use CloudEnvironment is        : ${params.UseCloudEnv}</i></p>
           <p style="color:DodgerBlue;"><i>The Cloud Environemt Name is   : ${params.CloudEnvName}</i></p>
           <p style="color:DodgerBlue;"><i>The Operating System is        : ${params.Os}</i></p>
           <p style="color:DodgerBlue;"><i>The Operating System version is: ${params.Os_Version}</i></p>
           <p style="color:DodgerBlue;"><i>The Browser Name is            : ${params.Browser_Name}</i></p>
           <p style="color:DodgerBlue;"><i>The Browser Version is         : ${params.Browser_Version}</i></p>
           <p style="color:DodgerBlue;"><i>The Implicitly Wait Time is    : ${params.ImplicitlyWaitTime}</i></p>
           <p style="color:DodgerBlue;"><i>The Secret File Path is        : ${params.SecretFilePath}</i></p>
           <p style="border:2px solid DodgerBlue;"><i>(Cucumber reports are attached.)</i></p>
           <p style="color:DodgerBlue;"><i>(Extent reports are attached.)</i></p>
           <p style="color:rgb(255, 99, 71);"><i>(Build log is attached.)</i></p>"""
cucumber fileIncludePattern: 'target/reports/cucumber-reports/cucumber.json', sortingMethod: 'ALPHABETICAL'
}
}
}
