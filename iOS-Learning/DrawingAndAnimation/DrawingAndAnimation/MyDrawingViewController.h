//
//  MyDrawingViewController.h
//  Drawing
//
//  Created by User on 8/8/13.
//  Copyright (c) 2013 BNR. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MyDrawingView;
@interface MyDrawingViewController : UIViewController
{
    MyDrawingView* myView;
    CGPoint myNewPosition;
}

-(void)startMovingCircle;

@end
