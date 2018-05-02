package com.example.demo;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.TextNode;

@Controller
public class FlowerController {

	private final AtomicLong counter = new AtomicLong();
	private DataSource source = new FlowerSource();
	public static final String url = "http://jsonplaceholder.typicode.com/posts";

	@Autowired
	public FlowerController(FlowerSource source) {
		this.source = source;
	}

	@GetMapping(value = "/feed", produces = "application/json")
	@ResponseBody
	@SuppressWarnings("unused")
	public ArrayNode feed() {

		final String response = source.getFeed(url);
		final ArrayNode root = source.getArrayNode(response);
		source.modify(root);
		return root;
	}

	@GetMapping("/count")
	@ResponseBody
	@SuppressWarnings("unused")
	public ResponseFlower count() {

		final String response = source.getFeed(url);
		final ArrayNode root = source.getArrayNode(response);
		final Map<String, JsonNode> idMap = source.createMap(root);
		return new ResponseFlower(counter.incrementAndGet(), new TextNode("" + idMap.size()));

	}

}