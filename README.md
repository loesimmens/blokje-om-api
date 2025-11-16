# Blokje Om API
API for the Blokje Om web app.

## Running on a server
1. Clone the repository:
   ```bash
   git clone
    ```
2. Navigate to the project directory:
   ```bash
   cd blokje-om-api
   ```
3. Run the docker stack:
   ```bash
   docker stack deploy -c docker-compose.yaml bo
   ```
4. If a container is not starting, check the logs with:
   ```bash
   docker stack ps --no-trunc bo 
   ```   
5. Stop the docker stack:
   ```bash
   docker stack rm bo
   ```
6. Remove the database volume **(this will empty the database!)**: 
   ```bash
   docker volume rm bo_db-data
   ```


