package strategy;

import java.util.Arrays;
import java.util.List;

enum Strategy {
	MARKDOWN, HTMLLIST
}

// interface
interface DynamicListStrategy {
	default void start(StringBuilder sb) {
	}

	void addListItem(StringBuilder stringBuilder, String item);

	default void end(StringBuilder sb) {
	}
}

// dynamic strategy algorithm
class DynamicMarkdownListStrategy implements DynamicListStrategy {

	@Override
	public void addListItem(StringBuilder stringBuilder, String item) {
		stringBuilder.append(" * ").append(item).append(System.lineSeparator());
	}
}

// dynamic strategy algorithm
class DynamicHtmlListStrategy implements DynamicListStrategy {

	@Override
	public void start(StringBuilder sb) {
		sb.append("<ul>").append(System.lineSeparator());
	}

	@Override
	public void addListItem(StringBuilder stringBuilder, String item) {
		stringBuilder.append("  <li>").append(item).append("</li>").append(System.lineSeparator());
	}

	@Override
	public void end(StringBuilder sb) {
		sb.append("</ul>").append(System.lineSeparator());
	}
}

// factory method
class ListStrategyFactory {

	public static DynamicListStrategy getDynamicListStrategy(Strategy strategy) {
		switch (strategy) {
		case HTMLLIST:
			return createHtmlListStrategy();
		case MARKDOWN:
			return createMarkdownStrategy();
		}
		throw new IllegalArgumentException();
	}

	public static DynamicListStrategy createHtmlListStrategy() {
		return new DynamicHtmlListStrategy();
	}

	public static DynamicListStrategy createMarkdownStrategy() {
		return new DynamicMarkdownListStrategy();
	}
}

class DynamicTextProcessor {

	private StringBuilder sb = new StringBuilder();
	private DynamicListStrategy listStrategy;

	public DynamicTextProcessor(DynamicListStrategy ls) {
		listStrategy = ls;
	}

	public void appendList(List<String> items) {
		listStrategy.start(sb);
		for (String item : items) {
			listStrategy.addListItem(sb, item);
		}
		listStrategy.end(sb);
	}

	public void clear() {
		sb.setLength(0);
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}

public class DynamicStrategyDemo {
	public static void main(String[] args) {
		DynamicTextProcessor tp = new DynamicTextProcessor(
				ListStrategyFactory.getDynamicListStrategy(Strategy.HTMLLIST));
		tp.appendList(Arrays.asList("a", "b", "c"));
		System.out.println(tp);

		tp = new DynamicTextProcessor(ListStrategyFactory.getDynamicListStrategy(Strategy.MARKDOWN));
		tp.appendList(Arrays.asList("a", "b", "c"));
		System.out.println(tp);
	}
}
