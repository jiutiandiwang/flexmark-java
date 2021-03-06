package com.vladsch.flexmark.youtrack.converter;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataHolder;
import com.vladsch.flexmark.youtrack.converter.internal.YouTrackConverterNodeRenderer;

/**
 * Extension for youtrack_converters
 * <p>
 * Create it with {@link #create()} and then configure it on the builders
 * ({@link com.vladsch.flexmark.parser.Parser.Builder#extensions(Iterable)},
 * {@link com.vladsch.flexmark.html.HtmlRenderer.Builder#extensions(Iterable)}).
 * </p>
 * <p>
 * The markdown AST is turned into YOUTRACK formatted text
 * </p>
 */
public class YouTrackConverterExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    private YouTrackConverterExtension() {
    }

    public static Extension create() {
        return new YouTrackConverterExtension();
    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {
        final String rendererType = HtmlRenderer.TYPE.getFrom(options);
        if (rendererType.equals("HTML")) {
            options.set(HtmlRenderer.TYPE, "YOUTRACK");
        } else if (!rendererType.equals("YOUTRACK")) {
            throw new IllegalStateException("Non HTML Renderer is already set to " + rendererType);
        }
    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
        switch (rendererType) {
            case "YOUTRACK":
                rendererBuilder.nodeRendererFactory(new YouTrackConverterNodeRenderer.Factory());
                break;

            default:
                throw new IllegalStateException("YouTrack Converter Extension used with non YouTrack Renderer " + rendererType);
        }
    }
}
