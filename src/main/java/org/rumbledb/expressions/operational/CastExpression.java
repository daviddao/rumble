package org.rumbledb.expressions.operational;

import org.rumbledb.exceptions.ExceptionMetadata;
import org.rumbledb.expressions.AbstractNodeVisitor;
import org.rumbledb.expressions.Expression;
import org.rumbledb.expressions.flowr.FlworVarSingleType;
import org.rumbledb.expressions.operational.base.UnaryExpressionBase;

public class CastExpression extends UnaryExpressionBase {

    private FlworVarSingleType singleType;

    public CastExpression(Expression mainExpression, FlworVarSingleType singleType, ExceptionMetadata metadata) {
        super(mainExpression, Operator.CAST, metadata);
        this.singleType = singleType;
    }

    @Override
    public <T> T accept(AbstractNodeVisitor<T> visitor, T argument) {
        return visitor.visitCastExpression(this, argument);
    }

    @Override
    public String serializationString(boolean prefix) {
        String result = "(castExpr ";
        result += this.mainExpression.serializationString(true);
        result += this.singleType != null ? " cast as" + this.singleType.serializationString(prefix) : "";
        result += ")";
        return result;
    }

    public FlworVarSingleType getFlworVarSingleType() {
        return this.singleType;
    }
}
