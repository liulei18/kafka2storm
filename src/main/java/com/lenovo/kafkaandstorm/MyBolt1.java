package com.lenovo.kafkaandstorm;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class MyBolt1 extends BaseRichBolt {
    private OutputCollector collector;
    private JedisPool pool;
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        this.collector = collector;
    }

    @Override
    public void execute(Tuple tuple) {
        String string = new String((byte[]) tuple.getValue(0));
        System.out.println(string);
        collector.ack(tuple);
    }

    private String getBubyProductId(String productId,String type) {
//        key:value
        //index:productID:info---->Map
        //  productId-----<各个业务线，各个品类，各个店铺，各个品牌，每个商品>
        Map<String,String> map =  new HashMap<>();
        map.put("b","3c");
        map.put("c","phone");
        map.put("s","121");
        map.put("p","iphone");
        return map.get(type);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
