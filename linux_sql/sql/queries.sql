--Query 1
SELECT cpu_number,
       id,
       total_mem
FROM host_info
GROUP BY cpu_number, id, total_mem
ORDER BY cpu_number, total_mem DESC;

--Query 2 & 3 Function
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

--Query 2
SELECT host_id,
       hostname,
       round5(host_usage.timestamp) AS timestamp_rounded,
       ROUND(AVG((total_mem - memory_free*1000.0)*100/total_mem),2) AS avg_mem_used
FROM host_usage
INNER JOIN host_info
    ON host_usage.host_id = host_info.id
GROUP BY 1, 2, 3
ORDER BY 3 asc;

--Query 3
SELECT host_id,
       round5(host_usage.timestamp) AS timestamp_rounded,
       COUNT(*) AS num_data_points
FROM host_usage
         INNER JOIN host_info
                    ON host_usage.host_id = host_info.id
GROUP BY 1, 2
HAVING COUNT(*) < 3
ORDER BY 2 asc;
