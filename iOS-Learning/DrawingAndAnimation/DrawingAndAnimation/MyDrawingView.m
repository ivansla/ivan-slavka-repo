//
//  MyDrawingView.m
//  Drawing
//
//  Created by User on 8/8/13.
//  Copyright (c) 2013 BNR. All rights reserved.
//

#import "MyDrawingView.h"

@implementation MyDrawingView

#define RADIUS (20.0)

-(id)init{
    self = [super initWithFrame:CGRectMake(0.0, 0.0, RADIUS * 2, RADIUS * 2)];
    if (self) {
        xCoordinate = 100.0;
        yCoordinate = 100.0;
        speed = 1.0;
    }
    return self;
}

- (void)drawRect:(CGRect)rect
{
    NSLog(@"Drawing");
    CGContextRef myContext =  UIGraphicsGetCurrentContext();
    CGContextBeginPath(myContext);
    CGContextAddArc(myContext, RADIUS, RADIUS, RADIUS, 0.0, 2 * M_PI, 0);
    CGContextClosePath(myContext);
    
    CGContextSetLineWidth(myContext, 1);
    CGContextSetStrokeColorWithColor(myContext, [UIColor whiteColor].CGColor);
    CGContextStrokePath(myContext);
}

-(CGPoint)position{
    return CGPointMake(xCoordinate, yCoordinate);
}

@end
