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
3. Install docker:
   https://docs.docker.com/engine/install/ubuntu/
4. Install docker swarm:
   ```bash
   docker swarm init --advertise-addr <your-server-ip>
   ```
5. Create docker secrets:
   ```bash
   docker secret create email_password -
   docker secret create db_host - #server-ip
   docker secret create postgres_password -
   docker secret create rebrickable_api_key -
   docker secret create keystore_password -
   ```
6. Run the docker stack:
   ```bash
   docker stack deploy -c docker-compose.yaml bo
   ```
7. If a container is not starting, check the logs with:
   ```bash
   docker stack ps --no-trunc bo 
   ```   
8. Stop the docker stack:
   ```bash
   docker stack rm bo
   ```
9. Remove the database volume **(this will empty the database!)**: 
   ```bash
   docker volume rm bo_db-data
   ```


