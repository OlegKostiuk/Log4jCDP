package com.epam.kostiuk.log4jfeature.levels;

import org.apache.log4j.Level;

/**
 * Custom level implementation, it is lower then WARN and higher then INFO
 */
public class CustomLevel extends Level {

	public static final int CUSTOM_INT = WARN_INT + 10;
	private static final String NAME = "CUSTOM";

	public static final Level CUSTOM = new CustomLevel(
			CUSTOM_INT, NAME, 10);

	protected CustomLevel(int arg0, String arg1, int arg2) {
		super(arg0, arg1, arg2);
	}

	public static Level toLevel(String logArgument) {
		if (logArgument != null
				&& logArgument.toUpperCase().equals(NAME)) {
			return CUSTOM;
		}
		return (Level) toLevel(logArgument);
	}

	public static Level toLevel(int val) {
		if (val == CUSTOM_INT) {
			return CUSTOM;
		}
		return (Level) toLevel(val, Level.WARN);
	}

	public static Level toLevel(int val, Level defaultLevel) {
		if (val == CUSTOM_INT) {
			return CUSTOM;
		}
		return Level.toLevel(val, defaultLevel);
	}

	public static Level toLevel(String logArgument, Level defaultLevel) {
		if (logArgument != null
				&& logArgument.toUpperCase().equals(NAME)) {
			return CUSTOM;
		}
		return Level.toLevel(logArgument, defaultLevel);
	}
}