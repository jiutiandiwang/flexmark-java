package com.vladsch.flexmark.ext.spec.example;

import com.vladsch.flexmark.internal.util.sequence.BasedSequence;
import com.vladsch.flexmark.node.CustomNode;

/**
 * A SpecExample block node
 */
public class SpecExampleAst extends CustomNode<SpecExampleVisitor> {
    @Override
    public void getAstExtra(StringBuilder out) {
        astExtraChars(out);
    }

    @Override
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public SpecExampleAst() {
    }

    public SpecExampleAst(BasedSequence chars) {
        super(chars);
    }

    @Override
    public void accept(SpecExampleVisitor visitor) {
        visitor.visit(this);
    }
}