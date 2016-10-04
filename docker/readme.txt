Tested with docker 1.12 / docker-machine on MacOS X (10.11)

0. Start docker-machine

    (If machine is not existing, create it first)

    docker-machine create dev --driver virtualbox

    docker-machine start dev

1. Create environment

    eval $(docker-machine env dev)

1. Build the image: docker build --no-cache --rm=true -t honeyalarmg2 .

2. docker run -p 8080:8080 -t -i honeyalarmg2

