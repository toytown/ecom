package com.ecom.common.utils;

import com.mysema.query.mongodb.Point;
import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Operator;
import com.mysema.query.types.OperatorImpl;
import com.mysema.query.types.Path;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.BooleanOperation;

public class PointExt extends Point {

	private static final long serialVersionUID = 1L;
	public static final Operator<Boolean> WITHIN = new OperatorImpl<Boolean>("WITHIN", Number.class, Number.class, Number.class, Number.class);
    
    public PointExt(Path<?> parent, String property) {
        super(parent, property);
    }

    
    public BooleanExpression within(double latVal1, double longVal1, double latVal2, double longVal2){
        return BooleanOperation.create(WITHIN, this, new ConstantImpl<Double[]>(new Double[]{latVal1, longVal1, latVal2, longVal2}));
    }    
}
