#! /bin/sh

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]; then
  echo 'host_info requires 5 arguments: psql_host, psql_port, db_name, psql_user, psql_password'
  exit 1
fi

vmstat_mb=$(vmstat --unit M)
hostname=$(hostname -f)
lscpu_out=`lscpu`

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | sed 's/Model name: //')
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
L2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | egrep -o -E "[0-9]+" | xargs)
total_mem=$(cat /proc/meminfo  | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp="`date +%Y-%m-%d` `date +%T`"

insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, \
  cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) \
  VALUES('$hostname', '$cpu_number', '$cpu_architecture', \
  '$cpu_model', '$cpu_mhz', '$L2_cache', '$total_mem', '$timestamp');"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?

