package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponseFlower {

	private final long responseId;
	private final JsonNode content;

	public ResponseFlower(long id, JsonNode node) {
		this.responseId = id;
		this.content = node;
	}

	public long getId() {
		return responseId;
	}

	public JsonNode getContent() {
		return content;
	}

}