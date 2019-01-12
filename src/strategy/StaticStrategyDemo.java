package strategy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

interface ListStrategy
{
  default void start(StringBuilder sb) {}
  void addListItem(StringBuilder stringBuilder, String item);
  default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy{
  @Override
  public void addListItem(StringBuilder stringBuilder, String item){
    stringBuilder.append(" * ").append(item)
      .append(System.lineSeparator());
  }
}

class HtmlListStrategy implements ListStrategy{
  @Override
  public void start(StringBuilder sb){
    sb.append("<ul>").append(System.lineSeparator());
  }

  @Override
  public void addListItem(StringBuilder stringBuilder, String item){
    stringBuilder.append("  <li>")
      .append(item)
      .append("</li>")
      .append(System.lineSeparator());
  }

  @Override
  public void end(StringBuilder sb)
  {
    sb.append("</ul>").append(System.lineSeparator());
  }
}

class TextProcessor<LS extends ListStrategy>
{
  private StringBuilder sb = new StringBuilder();
  // cannot do this
  // private LS listStrategy = new LS();
  private LS listStrategy;

  public TextProcessor(Supplier<? extends LS> ctor)
  {
    listStrategy = ctor.get();
  }

  // the skeleton algorithm is here
  public void appendList(List<String> items)
  {
    listStrategy.start(sb);
    for (String item : items)
      listStrategy.addListItem(sb, item);
    listStrategy.end(sb);
  }

  public void clear()
  {
    sb.setLength(0);
  }

  @Override
  public String toString()
  {
    return sb.toString();
  }
}

public class StaticStrategyDemo
{
  public static void main(String[] args)
  {
	/*
	 * passing the object type makTextProcessor<"MarkdownListStrategy"> tp = new TextProcessor<>(MarkdownListStrategy::new) 
	 * makes it impossible to change the type thus making it static strategy... it is less flexible
	 * 
	 * inorder to make it dynamic pass ListStrategy interface so that tp can refer to any (MarkdownListStrategy, HtmlListStrategy)
	 */
   
	TextProcessor<MarkdownListStrategy> tp = new TextProcessor<>(MarkdownListStrategy::new);
    tp.appendList(Arrays.asList("a", "b", "c"));
    
    System.out.println(tp);
    
    TextProcessor<HtmlListStrategy> tp2 = new TextProcessor<>(HtmlListStrategy::new);
    tp2.appendList(Arrays.asList("a","b","c"));
    System.out.println(tp2);
  }
}