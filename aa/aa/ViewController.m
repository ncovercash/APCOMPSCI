//
//  ViewController.m
//  aa
//
//  Created by 2A Overcash, N on 9/29/16.
//  Copyright © 2016 smileytechguy. All rights reserved.
//

#import "ViewController.h"

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
        self.view.layer.backgroundColor = [NSColor greenColor].CGColor;
        self.view.potatotextField.adjustsFontSizeToFitWidth = true

    // Do any additional setup after loading the view.
}

- (void)setRepresentedObject:(id)representedObject {
    [super setRepresentedObject:representedObject];

    // Update the view, if already loaded.
}

@end
