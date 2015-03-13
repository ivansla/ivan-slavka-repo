//
//  MyDrawingView.h
//  Drawing
//
//  Created by User on 8/8/13.
//  Copyright (c) 2013 BNR. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MyDrawingView : UIView
{
    double xCoordinate;
    double yCoordinate;
    double speed;
}

-(id)init;

-(CGPoint)position;

@end
