/*
 * This file is part of exp4j.
 *
 * Copyright (c) 2011 Frank Asseg
 * Modifications (c) 2012 Spout LLC <http://www.spout.org/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.congrace.exp4j.token;

import java.util.Stack;

import gnu.trove.map.TObjectDoubleMap;
import gnu.trove.stack.TDoubleStack;

/**
 * A {@link Token} for Numbers.
 *
 * @author fas@congrace.de
 */
public class NumberToken extends CalculationToken {
	private final double doubleValue;

	/**
	 * Construct a new {@link NumberToken}.
	 *
	 * @param value the value of the number as a {@link String}.
	 */
	public NumberToken(String value) {
		super(value);
		if (value.indexOf('E') > 0 || value.indexOf('e') > 0) {
			final int pos = value.toLowerCase().indexOf('e');
			doubleValue = Double.parseDouble(value.substring(0, pos)) * Math.pow(10, Double.parseDouble(value.substring(pos + 1)));
		} else {
			doubleValue = Double.parseDouble(value);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NumberToken) {
			final NumberToken t = (NumberToken) obj;
			return t.getValue().equals(this.getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public void mutateStackForCalculation(TDoubleStack stack, TObjectDoubleMap<String> variableValues) {
		stack.push(this.doubleValue);
	}

	@Override
	public void mutateStackForInfixTranslation(Stack<Token> operatorStack, StringBuilder output) {
		output.append(this.getValue()).append(' ');
	}
}
