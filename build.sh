services=("auth" "billetera" "email" "estacion" "informe" "reserva" "servicio" "suscripcion" "usuario")

for service in "${services[@]}"
do
  echo "Building $service..."
  (cd $service && ./gradlew build -x test)
done

echo "Building Docker images..."
docker compose build