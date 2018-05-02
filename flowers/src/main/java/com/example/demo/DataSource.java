package com.example.demo;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public interface DataSource {

	String getFeed(String url);

	ArrayNode getArrayNode(String response);

	ArrayNode modify(ArrayNode root);

	Map<String, JsonNode> createMap(ArrayNode root);

}
