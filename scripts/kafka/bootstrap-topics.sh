echo "Waiting for Kafka to come online..."

cub kafka-ready -b kafka1:19092 1 20

echo "Creating Kafka topics"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.fct.files.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.fct.files.0 - Created"


kafka-topics \
  --bootstrap-server kafka1:19092 \echo "Waiting for Kafka to come online..."

cub kafka-ready -b kafka1:19092 1 20

echo "Creating Kafka topics"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.fct.files.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.fct.files.0 - Created"


kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.fct.filestatus.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.fct.filestatus.0 - Created"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.view.files.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.view.files.0 - Created"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.view.filestatus.0\
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.view.filestatus.0 - Created"

#echo "Pre-populating Kafka topics"
## pre-populate the topic
#kafka-console-producer \
#  --bootstrap-server kafka:9092 \
#  --topic tweets \
#  --property 'parse.key=true' \
#  --property 'key.separator=|' < /data/inputs.txt

sleep infinity
  --topic statement.fct.filestatus.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.fct.filestatus.0 - Created"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.view.files.0 \
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.view.files.0 - Created"

kafka-topics \
  --bootstrap-server kafka1:19092 \
  --topic statement.view.filestatus.0\
  --replication-factor 1 \
  --partitions 3 \
  --create

echo "Topic: statement.view.filestatus.0 - Created"

#echo "Pre-populating Kafka topics"
## pre-populate the topic
#kafka-console-producer \
#  --bootstrap-server kafka:9092 \
#  --topic tweets \
#  --property 'parse.key=true' \
#  --property 'key.separator=|' < /data/inputs.txt

sleep infinity