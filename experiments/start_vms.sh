


#catalogue db
docker run --name catalogue-db --hostname catalogue-db -it weaveworksdemos/catalogue-db:0.3.0 /bin/bash

#catalogue
docker run --name catalogue --hostname catalogue -it weaveworksdemos/catalogue:0.3.5

#start user-sim
docker run --net=host weaveworksdemos/load-test -h 35.234.130.160 -r 100 -c 2

