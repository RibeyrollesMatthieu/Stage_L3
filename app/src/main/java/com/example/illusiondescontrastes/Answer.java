package com.example.illusiondescontrastes;

enum Answer {
	LIGHTER ( "-" ),
	EQUALS ( "=" ),
	DARKER ( "+");

	private final String value;

	String getValue() { return this.value; }

	Answer( String value ) {
		this.value = value;
	}
}
