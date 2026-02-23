# Microservices-Project

1st microservice project

# for docker run

docker-compose down -v
docker-compose build --no-cache
docker-compose up -d

# for minikube

kubectl delete all --all
kubectl get all
minikube status
minikube start --driver=docker
minikube docker-env --shell powershell | Invoke-Expression
docker build -t auth-service ./auth
docker build -t product-service ./product
docker build -t api-gateway ./api-gateway
kubectl get pods
kubectl get pods -w
kubectl create configmap mysql-init-config --from-file=mysql-init/init.sql
kubectl apply -f mysql-deployment.yaml
kubectl apply -f auth-deployment.yaml
kubectl apply -f product-deployment.yaml
kubectl apply -f api-gateway-deployment.yaml
minikube service api-gateway --url
minikube service product-service-pod --url
minikube service product-service --url

