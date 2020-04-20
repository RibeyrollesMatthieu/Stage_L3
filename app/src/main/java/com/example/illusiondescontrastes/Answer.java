package com.example.illusiondescontrastes;

enum Answer {
	LIGHTER ( "PLus clair" ),
	EQUALS ( "Ce sont les même" ),
	DARKER ( "Plus foncé");

	private final String value;

	String getValue() { return this.value; }

	Answer( String value ) { this.value = value; }
}
