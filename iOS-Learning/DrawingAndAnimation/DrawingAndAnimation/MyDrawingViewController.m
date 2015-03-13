//
//  MyDrawingViewController.m
//  Drawing
//
//  Created by User on 8/8/13.
//  Copyright (c) 2013 BNR. All rights reserved.
//

#import "MyDrawingViewController.h"
#import "MyDrawingView.h"
#import "QuartzCore/CAAnimation.h"

@implementation MyDrawingViewController

-(id)init{
    self = [super init];
    if (self){
        
        myView = [[MyDrawingView alloc] init];
        [[self view] addSubview:myView];
        [[self view] setBackgroundColor:[UIColor blackColor]];
        
    }
    return self;
}

-(void)startMovingCircle{
    
    CGPoint p = myView.center;
    NSLog(@"P: ,%f, %f", p.x, p.y);
    CGPoint pOrig = p;
    NSLog(@"P: ,%f, %f", p.x, p.y);
    NSLog(@"pOrig: ,%f, %f", pOrig.x, pOrig.y);
    p.x += 100;
    NSLog(@"P: ,%f, %f", p.x, p.y);
    NSLog(@"pOrig: ,%f, %f", pOrig.x, pOrig.y);
    void (^anim) (void) = ^{
        myView.center = p;
        NSLog(@"Layer Coordinates: %f, %f", myView.center.x, myView.center.y);
    };
    
    void (^after) (BOOL) = ^(BOOL f){
        myView.center = p;//Orig;
        NSLog(@"Layer Coordinates: %f, %f", myView.center.x, myView.center.y);
    };
    
    [UIView animateWithDuration:1.0 animations:anim completion:after];
}

@end
