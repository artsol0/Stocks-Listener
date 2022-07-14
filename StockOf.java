import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class StockOf {

    private static String getHTML(String inc) {
        In page = new In("https://finance.yahoo.com/quote/" + inc);
        return page.readAll();
    }

    public static String priceChanges(String inc) {
        String html = getHTML(inc);

        int s = html.indexOf("qsp-price-change",0);
        int from = html.indexOf(">",s);
        int to = html.indexOf("</span>",from);

        String priceChange = html.substring(from + 33, to);

        return priceChange;
    }

    public static String priceChangePercent(String inc) {
        String html = getHTML(inc);

        int s = html.indexOf("regularMarketChangePercent",0);
        int from = html.indexOf(">(",s);
        int to = html.indexOf(")</span>",from);

        String priceChangePercent = html.substring(from + 2, to);

        return priceChangePercent;
    }
    public static double priceOf(String inc) {
        String html = getHTML(inc);

        int s = html.indexOf("qsp-price",0);
        int from = html.indexOf(">",s);
        int to = html.indexOf("</fin-streamer>",from);

        String price = html.substring(from + 1, to);

        return Double.parseDouble(price.replace(",",""));
    }

    public static String getName(String inc) {
        String html = getHTML(inc);

        int s = html.indexOf("<div class=\"D(ib) Mt(-5px) Maw(38%)--tab768 Maw(38%) Mend(10px) Ov(h) smartphone_Maw(85%) smartphone_Mend(0px)\"",0);
        int from = html.indexOf(">",s);
        int to = html.indexOf("</h1></div>",from);

        String name = html.substring(from + 64, to);

        return name;
    }

    public static void goBuy(String inc) throws IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(URI.create("https://www.nasdaq.com/market-activity/stocks/" + inc));
    }


    public static void getStocks(String inc) {
        System.out.println("We are going to the market.. pleas wait...");
        System.out.println();
        System.out.println(getName(inc));
        System.out.println("Stock price:\t" + priceOf(inc) + "$");
        System.out.println("Price changes:\t" + priceChanges(inc) + "(" + priceChangePercent(inc) + ")");
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        String inc = args[0];
        getStocks(inc);
        goBuy(inc);
    }

}
