package com.vladsch.flexmark.ext.emoji;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.jira.converter.JiraConverterExtension;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.spec.SpecReader;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.junit.runners.Parameterized;

import java.util.*;

public class ComboEmojiJiraTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/ext_emoji_jira_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(HtmlRenderer.RENDER_HEADER_ID, false)
            .set(HtmlRenderer.GENERATE_HEADER_ID, true)
            //.set(HtmlRenderer.PERCENT_ENCODE_URLS, true)
            .set(Parser.EXTENSIONS, Arrays.asList(JiraConverterExtension.create(), EmojiExtension.create()));

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        //optionsMap.put("src-pos", new MutableDataSet().set(HtmlRenderer.SOURCE_POSITION_ATTRIBUTE, "md-pos"));
        //optionsMap.put("no-wrap", new MutableDataSet().set(AnchorLinkExtension.ANCHORLINKS_WRAP_TEXT, false));
        //optionsMap.put("set-name", new MutableDataSet().set(AnchorLinkExtension.ANCHORLINKS_SET_NAME, true));
        //optionsMap.put("no-id", new MutableDataSet().set(AnchorLinkExtension.ANCHORLINKS_SET_ID, false));
        //optionsMap.put("no-class", new MutableDataSet().set(AnchorLinkExtension.ANCHORLINKS_ANCHOR_CLASS, ""));
        //optionsMap.put("prefix-suffix", new MutableDataSet()
        //        .set(AnchorLinkExtension.ANCHORLINKS_TEXT_PREFIX, "<span class=\"anchor\">")
        //        .set(AnchorLinkExtension.ANCHORLINKS_TEXT_SUFFIX, "</span>")
        //);
    }

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    private static DataHolder optionsSet(String optionSet) {
        if (optionSet == null) return null;
        return optionsMap.get(optionSet);
    }

    public ComboEmojiJiraTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        List<SpecExample> examples = SpecReader.readExamples(SPEC_RESOURCE);
        List<Object[]> data = new ArrayList<>();

        // NULL example runs full spec test
        data.add(new Object[] { SpecExample.NULL });

        for (SpecExample example : examples) {
            data.add(new Object[] { example });
        }
        return data;
    }

    @Override
    public DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    public String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    public Parser parser() {
        return PARSER;
    }

    @Override
    public HtmlRenderer renderer() {
        return RENDERER;
    }
}
