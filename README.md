# Terl Bank acount mangment system 
**taks requiremtns**
 Your task is to develop a backend for a banking platform capable of serving the following functions through APIs:
 1-Withdraw cash from an account
 2-  Deposit cash to an account
 3- Transfer money from one account to another
# Main Component digram 
  there are three microservices 
  
1.**Account Management**: Responsible for processing operations such as creating accounts, withdrawing cash, and depositing cash.

2.**Account Enquery**: Responsible for handling read operations related to accounts.  

3.**Fund transfer**: Manages fund transfers between accounts, both internal and external, and also maintains transaction history.

<img width="502" alt="Screenshot 2023-11-25 at 2 21 00 PM" src="https://github.com/ahmedosm/saga-axon-server-spring-boot/assets/9503646/7150f015-4e1b-4b3c-b748-1d050f5a4573">

# Fund Transfer Solution
<img width="787" alt="Screenshot 2023-11-25 at 6 04 26 PM" src="https://github.com/ahmedosm/saga-axon-server-spring-boot/assets/9503646/c8bfcddd-10c1-473f-9360-cc7f7785abd6">

**External Fund Transfer**
1. Save Transaction as Initial
Save the transaction details as the initial step.
2. Trigger Money Hold Event
Initiate a money hold event, transferring funds internally from the source account to our GL account.
3. Receive Hold GL Amount Acknowledgment
Wait for acknowledgment confirming the successful hold of the GL amount.
4. Process Money to Swift
If the hold is successful, proceed to process the money to Swift.
If successful, initiate the GL clearance process to withdraw the amount from GL to the Swift settlement account.
5. Handle Swift Message Failure
If the Swift message is not successful, initiate the refund process, transferring money from the GL account back to the source account.

## Technical solution

**Microservices Architecture for Business Component Segregation and High Availability**

1. Utilizing a microservices architecture to segregate business components ensures a modular and scalable design.
2. The architecture enhances high availability by allowing independent scaling and deployment of microservices.
3. Each microservice focuses on a specific business capability, promoting maintainability and flexibility.
  
**Geographical Saga Pattern for Fund Transfer Process with Kafka Communication**
1. Employing the geographical Saga pattern to manage the fund transfer process.
2. Kafka serves as the communication backbone between microservices, ensuring reliable and asynchronous communication.
   he Saga pattern helps maintain consistency in distributed transactions across multiple microservices.
   
**Event Sourcing for Account State Management and Persistence**

1. Leveraging Event Sourcing to manage the state of accounts.
2. Events represent state changes, providing a historical record of account transactions.
3. This approach facilitates auditability, traceability, and rebuilding the account state at any point in time.
   
**CQRS (Command Query Responsibility Segregation) for High Availability**

1. Implementing CQRS to segregate the command (write) and query (read) responsibilities.
2. Enhances high availability by allowing for independent scaling of read and write components.
3. Write operations and read queries are handled by separate services, optimizing performance and resource utilization.


 



## Automated CI/CD Pipeline Process

### Process Overview

The diagram below illustrates the automated CI/CD pipeline process for deploying new code changes to the main branch:

```mermaid
graph LR
    A[New Code Pushed to Main Branch] -->|Triggers| B(Build Pipeline)
    B -->|Builds and Pushes Docker Image| C[Azure Container Registry]
    C -->|Image Pushed| D(Release Pipeline in Azure)
    D -->|Deploys to| E[Azure Kubernetes Service]
```

### Process Description

1. **Code Commit to Main Branch**: Developers push their new code changes to the main branch in the repository. This is the starting point of the CI/CD pipeline.

2. **Build Pipeline Activation**: The push to the main branch triggers an automated build pipeline. This pipeline is responsible for compiling the code, running tests, and building the application, thereby creating a new version.

3. **Docker Image Creation and Push**: Upon a successful build, the pipeline creates a Docker image of the application. This Docker image is then pushed to the Azure Container Registry, a private registry for hosting container images.

4. **Release Pipeline Trigger**: The successful push of the Docker image to the Azure Container Registry automatically triggers the release pipeline in Azure DevOps.

5. **Deployment to Azure Kubernetes Service (AKS)**: The release pipeline takes the new Docker image and deploys it into the Azure Kubernetes Service. AKS manages the deployment, scaling, and operations of the application within Kubernetes.
