# Learning Kafka

## Install Kafka with KRaft (Linux)

Requirements:

* Java 11.
* Apache Kafka v2.8+ under Binary.

1. Install Java 11
   * Download Java JDK 11 from [Amazon Corretto 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) or [Oracle Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
   * Check if Java is installed:
     * Command: `java -version`.
2. Install Kafka
   * Download [Apache Kafka](https://kafka.apache.org/downloads) under Binary downloads.
   * Extract the contents of the downloaded file.
   * Move the extracted folder to `/opt/kafka`:
     * Command: `sudo mv extracted_folder /opt/kafka`.
   * Add Kafka to the PATH:
   * Open the `.bashrc` file:
     * Command: `sudo nano ~/.bashrc`.
   * Add the following line at the end of the file:
     * `PATH=$PATH:/opt/kafka/bin`.

3. Configure KRaft
    * Create cluster ID
      * Command: `kafka-storage.sh random-uuid`.
      * The value generated is the cluster ID.
    * Format the storage directory:
      * Command: `kafka-storage.sh format -t <cluster_id> -c /opt/kafka/config/kraft/server.properties`.

4. Start Kafka Server
    * Command: `kafka-server-start.sh /opt/kafka/config/kraft/server.properties`.
    * Use `Ctrl + C` to stop the server.
