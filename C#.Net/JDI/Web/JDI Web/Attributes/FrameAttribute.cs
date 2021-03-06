﻿using System;
using System.Reflection;
using OpenQA.Selenium;

namespace JDI_Web.Attributes
{
    [AttributeUsage(AttributeTargets.Property | AttributeTargets.Field)]
    public class FrameAttribute : Attribute
    {
        private By _frameLocator;
        public string ById { set => _frameLocator = By.Id(value);
            get => "";
        }
        public string ByName { set => _frameLocator = By.Name(value);
            get => "";
        }
        public string ByClassName { set => _frameLocator = By.ClassName(value);
            get => "";
        }
        public string ByCssSelector { set => _frameLocator = By.CssSelector(value);
            get => "";
        }
        public string ByXPath { set => _frameLocator = By.XPath(value);
            get => "";
        }
        public string ByTagName { set => _frameLocator = By.TagName(value);
            get => "";
        }
        public string ByLinkText { set => _frameLocator = By.LinkText(value);
            get => "";
        }
        public string ByPartialLinkText { set => _frameLocator = By.PartialLinkText(value);
            get => "";
        }

        public FrameAttribute() { }
        public FrameAttribute(string frameId) { _frameLocator = By.Id(frameId); }

        public static By GetFrame(FieldInfo field)
        {
            var frame = field.GetCustomAttribute<FrameAttribute>(false);
            return frame?._frameLocator;
        }
    }
}
