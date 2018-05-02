package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

@Repository
public class FlowerSource implements DataSource {

	public FlowerSource() {
	}

	public ArrayNode getArrayNode(String response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response);
			return (ArrayNode) root;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayNode modify(ArrayNode root) {
		ObjectNode node = (ObjectNode) root.get(3);
		node.set("title", new TextNode("1800Flowers"));
		node.set("body", new TextNode("1800Flowers"));

		return root;
	}

	public HashMap<String, JsonNode> createMap(ArrayNode root) {
		final HashMap<String, JsonNode> map = new HashMap<String, JsonNode>();
		final Iterator<JsonNode> it = root.iterator();

		while (it.hasNext()) {
			JsonNode node = it.next();
			JsonNode idNode = node.get("userId");
			if (idNode != null) {
				String id = idNode.asText();
				map.put(id, node);
			}
		}
		return map;
	}

	public String getFeed(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return response.getBody();

	}

}
